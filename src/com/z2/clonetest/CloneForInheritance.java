package com.z2.clonetest;

/**
 * give subclass the choice to declare cloneable or not.
 * 
 * @author zhouzhou
 *
 */
public class CloneForInheritance {
	
	@Override protected CloneForInheritance clone() 
			throws CloneNotSupportedException{
		return (CloneForInheritance)super.clone();
	}

}
