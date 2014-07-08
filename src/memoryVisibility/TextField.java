package memoryVisibility;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

public class TextField {
//not we don't use bit fields
	enum Style{
		BOLD,
		ITALIC,
		UNDERLINE;
	}
	
	
	public void applyStyle(Set<Style> styles){
		
	}
	
	public static void main(String[] args)
	{
		TextField tf = new TextField();
		tf.applyStyle(EnumSet.of(Style.BOLD, Style.ITALIC));
		Map<Style, Set<Style>> styleMap = new EnumMap<Style, Set<Style>>(Style.class);
		
		styleMap.put(Style.BOLD, EnumSet.of(Style.BOLD, Style.ITALIC));
	}
	
}
