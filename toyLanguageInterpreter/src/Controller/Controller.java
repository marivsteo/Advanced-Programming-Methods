package Controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import ADTs.MyIDictionary;
import ADTs.MyIHeap;
import ADTs.MyIStack;
import Exceptions.MyException;
import ProgramState.PrgState;
import Repository.IRepository;
import Statements.IStmt;
import Values.RefValue;
import Values.Value;

public class Controller {
	
	private IRepository<PrgState> repo;
	private ExecutorService executor;
	
	public List<Integer> getAddressFromSymTable(MyIDictionary<String, Value> symTable)
	{
		return symTable.elements().stream()
			.filter(v-> v instanceof RefValue)
			.map(v-> {RefValue v1 = (RefValue)v; return v1.getAddress();})
			.collect(Collectors.toList());
	}
	
	public List<Integer> getAddressFromHeap(MyIHeap<Value> heap)
	{
		
		return heap.elements().stream()
				.filter(v-> v instanceof RefValue)
				.map(v-> {RefValue v1 = (RefValue)v; return v1.getAddress();})
				.collect(Collectors.toList());
	}
	
	public Map<Integer, Value> safeGarbageCollector(Collection<Integer> symTableAddress, Collection<Integer> heapAddress, MyIHeap<Value> heap)
	{
		Map<Integer, Value> heapContent =  heap.entrySet();
		return heapContent.entrySet().stream()
				.filter(e->symTableAddress.contains(e.getKey()) || heapAddress.contains(e.getKey()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}
	
	public Controller(IRepository<PrgState> repo_)
	{
		this.repo = repo_;
		this.executor =  Executors.newFixedThreadPool(16);
	}
	
	public void allStep() throws MyException
	{
		this.executor = Executors.newFixedThreadPool(16);
		List<PrgState> prgList=removeCompletedPrg(repo.getPrgList());
		
		while (prgList.size() > 0)
		{
			List<Integer> symTableAddress = new LinkedList<Integer>();
			for (int index = 0; index < prgList.size(); ++index)
			{
				List<Integer> auxTableAddress = this.getAddressFromSymTable( prgList.get(index).getDict());
				symTableAddress.addAll(auxTableAddress);
			}
			//The Heap is common to all of the Program States, so we can use prgList.get(0).getHeap().
			List<Integer> heapAddress = this.getAddressFromHeap(prgList.get(0).getHeap());
			prgList.get(0).getHeap().addEntrySet(this.safeGarbageCollector(symTableAddress, heapAddress, prgList.get(0).getHeap()));
			
			oneStepForAllPrg(prgList);
			prgList=removeCompletedPrg(repo.getPrgList());
		}
		this.executor.shutdownNow();
		repo.setPrgList(prgList);
	}
	
	public void addProgramState(PrgState state) throws MyException
	{
		this.repo.add(state);
	}
	
	public void oneStepForAllPrg(List<PrgState> prgList)
	{	
		List<Callable<PrgState>> callList = prgList.stream()
				.map((PrgState p) -> (Callable<PrgState>)(() -> {return p.oneStep();}))
				.collect(Collectors.toList());
		
		
		List<PrgState> newPrgList = null;
		try 
		{
			newPrgList = this.executor.invokeAll(callList). stream()
					. map(future -> {
							try {
								return future.get();
							} catch (InterruptedException e) { System.out.printf("Shita!");
							} catch (ExecutionException e) { e.printStackTrace(System.out);}
							return null;
							})
					.filter(p -> p!=null)
					.collect(Collectors.toList());
		} 
		catch (InterruptedException e) {System.out.printf("Shitc!");}
		
		prgList.addAll(newPrgList); //add the new created threads to the list of existing threads
		
		this.repo.setPrgList(prgList);
		
		prgList.forEach(prg ->{ try { repo.logPrgStateExec(prg); } catch (MyException e) {} });
		
	}
	
	public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList)
	{
		return inPrgList.stream()
				.filter(p -> p.isNotCompleted())
				.collect(Collectors.toList());
	}
}
