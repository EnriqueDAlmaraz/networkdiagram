import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class NetworkDiagram {
    private static List<Activity> activities;
    private static List<Path> paths;
	
	public static String createTree(List<Activity> listActivity) {
    	// Create activities list
		activities = listActivity;
		Collections.sort(activities);
		Activity[] nodes = new Activity[activities.size()];
    	
    	// Create Empty tree
    	int count = 0;
        for(Activity activity : activities) {
            nodes[count] = activity;
            nodes[count].setId(count);
            count++;
        }
        
        // Add predecessors as parents
        for(Activity node : nodes) {
        	for (Predecessor p : node.predecessors) {
        		for(Activity n : nodes) {
        			if (n.getName().equals(p.getName())) {
        				nodes[n.id].addChild(node);
        			}
        		}
        	}
        }
        
        // Create list of paths
        paths = new LinkedList<>();
        
        List<List<Activity>> lists = getPaths(nodes[0]);
        for(List<Activity> list : lists) {
        	// Get paths one at a time
        	Path path = new Path();
        	String pathName = "";
            for(count = 0; count < list.size(); count++) {
            	Activity currentNode = list.get(count);
            	path.addActivity(currentNode);
                pathName += currentNode.getName();
                if(count != list.size() - 1) {
                    pathName += "-";
                }
            }
            pathName+= ": " + path.getDuration() + "\n";
            path.setName(pathName);
            
            // Is this path already in the list?
            boolean exists = false;
            for (Path p: paths) {
            	if (p.getName().equals(path.getName())) {
            		exists = true;
            	}
            }
            if (!exists) {
            	paths.add(path);
            }
        }
        
        String output = "";
        Collections.sort(paths);
        for (Path p: paths) {
        	output += p.getName();
        }
        return output;
    }
	
    public static List<List<Activity>> getPaths(Activity head) {
        if(head == null) { 
            return new ArrayList<>();
        } else { 
        	// Recursively find each path with getEachPath()
        	List<List<Activity>> retLists = new ArrayList<>();

            if(head.getChildren().size() == 0) {
                List<Activity> leafList = new LinkedList<>();
                leafList.add(head);
                retLists.add(leafList);
            } else {
                for (Activity node : head.getChildren()) {
                    List<List<Activity>> nodeLists = getEachPath(node);
                    for (List<Activity> nodeList : nodeLists) {
                        nodeList.add(0, head);
                        retLists.add(nodeList);
                    }
                }
            }
            return retLists;
        }
    }
    
    // Recursive function to find each path
    private static List<List<Activity>> getEachPath(Activity pos) {
        List<List<Activity>> retLists = new ArrayList<>();

        if(pos.getChildren().size() == 0) {
            List<Activity> leafList = new LinkedList<>();
            leafList.add(pos);
            retLists.add(leafList);
        } else {
            for (Activity node : pos.getChildren()) {
                List<List<Activity>> nodeLists = getEachPath(node);
                for (List<Activity> nodeList : nodeLists) {
                    nodeList.add(0, pos);
                    retLists.add(nodeList);
                }
            }
        }
        return retLists;
    }
   
    public Iterator<Activity> iterator() {
		return activities.iterator();
	}
    
    public Iterator<Path> iterator1() {
		return paths.iterator();
	} 
}