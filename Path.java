import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * <center>
 * <table cellpadding="5" cellspacing="5">
 *  <tr>
 *  <td valign="top">
 *   Course: CSE 360<br>
 *   Section Line Number: 89049<br>
 *   Project: Activity Network<br>
 *  </td>
 *  
 *  <td valign="top">
 *   Contributor: Anthony Benites,<br>
 *   Arizona State Univeristy<br>
 *  </td>
 * 
 *  <td valign="top">
 *   Contributor: Luis Claramunt <br>
 *   Arizona State Univeristy<br>
 *  </td>
 * 
 * <td valign="top">
 *   Contributor: Enrique Almaraz<br>
 *   Arizona State Univeristy<br>
 *  </td>
 *  </tr>
 * </table>
 * </center>
 */
 
public class Path implements Comparable<Path> {
	private List<Activity> path;
	private String name;
	
	public Path() {
    	this.path = new LinkedList<>();
    }
    
    public int getDuration() {
    	int duration = 0;
    	for (Activity p : path) {
    		duration = duration + p.getDuration();
    	}
    	return duration;
    }

	public int compareTo(Path i) {
		return i.getDuration() - this.getDuration();
	}

	public void addActivity(Activity activity) {
		this.path.add(activity);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}

	public Iterator<Activity> iterator() {
		return this.path.iterator();
	}
}