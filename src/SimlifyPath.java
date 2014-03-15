import java.util.LinkedList;

/**
 * Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"
 * @author zhouzhou
 *
 */
public class SimlifyPath {

	public static void main(String[] args){
		System.out.println(simplifyPath("/home/.ssh/../.ssh2/authorized_keys/"));
	}
	
	public String simplifyPath1(String path) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(path == null) return null;
    
        int len = path.length();
        
        LinkedList<String> s = new LinkedList<String>();
        
        int i =0;
        int start = -1;
        int end = -1;
        String ele = null;
        while(i< len){
            
            while(i< len && path.charAt(i) == '/') i++;//rolling i to ignore staring /
            if(i == len) break;
            start = i;
            while(i< len && path.charAt(i) != '/') i++;//track words until sees the /.
            end = i;
            
            ele = path.substring(start, end);//words.
            if(ele.equals(".")){//bypass .
                continue;
            }else if(ele.equals("..")){//pop stack
                if(!s.isEmpty()){
                    s.pollLast();
                }
            }else{
                s.offer(ele);//store in stack
            }
            
            
        }
        
        if(s.isEmpty()) return "/";//if empty. just root.
        
        StringBuilder sb = new StringBuilder();
        while(!s.isEmpty()){
            sb.append('/').append(s.poll());//normal path concatenation.
        }
        return sb.toString();
    }
	public static  String simplifyPath(String path) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(path == null) return null;
        
        char[] chars = path.toCharArray();
        
        int start = -1;//to track path element start and end.
        int end = -1;
        
        char last = '/'; //default start char. no need to double escape /.
        
        LinkedList<String> s = new LinkedList<String>(); //double ended queue.
        //can pop from end.
        
        for(int i=0; i< chars.length; i++){
            if(last == '/' && Character.isLetterOrDigit(chars[i])){//letter digit check.
                start = i;//start of word
            }else if(Character.isLetterOrDigit(last) && chars[i] == '/'){
                end = i;//end of path element
                s.offer(path.substring(start, end));
                start = -1;
            }else if(chars[i] == '.' && last != '.'){
            	if(i+1 < chars.length && Character.isLetterOrDigit(chars[i+1])){
            		start = i; //could be hidden file name.
            	}else{
            		start = -1; //reset start otherwise.
            	}
            }else if(chars[i] == '.' && last == '.'){
                start = -1;
                //pop off stack if it is not empty.
                if(!s.isEmpty()){
                    s.pollLast();
                }
            }
            last = chars[i];
        }
        
        if(start != -1){
            end = chars.length;
            s.offer(path.substring(start, end));
        }//in case we don't get "/" as last char.
        //need to add last path ele.
        
        StringBuilder sb = new StringBuilder();
        sb.append('/');//output root.
        while(!s.isEmpty()){
            sb.append(s.poll()).append('/');
        }
        //only remove trailing / when it is more than root.
        if(sb.length() > 1){
            sb.setLength(sb.length()-1);//get rid of the trailing /
        }
        return sb.toString();
    }
}
