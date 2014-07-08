package memoryVisibility;

import java.util.EnumMap;
import java.util.Map;

/**
 * don't use ordinal index to get the transition
 * @author zhouzhou
 *
 */
public enum Phase {

	SOLID,
	LIQUID,
	GAS;
	
	public enum Transition{
		MELT(SOLID, LIQUID), 
		FREEZE(LIQUID, SOLID),
		BOIL(LIQUID, GAS),
		SUBLIME(SOLID, GAS);
		
		final Phase src;
		final Phase dest;
		
		Transition(Phase src, Phase dest){
			this.src = src;
			this.dest = dest;
		}
	}
	
	private static final Map<Phase, Map<Phase, Transition>> m = 
			 new EnumMap<Phase, Map<Phase, Transition>>(Phase.class);
	
	static {
		for(Phase phase : Phase.values()){
			m.put(phase, new EnumMap<Phase, Transition>(Phase.class));
		}
		
		for(Transition t: Transition.values()){
			m.get(t.src).put(t.dest, t);
		}
		
		
	}
	
	public static Transition from(Phase src, Phase dest){
		return m.get(src).get(dest);
	}
}
