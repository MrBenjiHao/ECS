/**
 * Created by Ben on 2/17/14.
 */
public class Component {
	private static String UID;
	private boolean enabled = false;

	public static String getUniqueID(){
		return UID;
	}

	public void disable(){
		enabled = false;
	}

	public void enable(){
		enabled = true;
	}

	public boolean isEnabled(){
		return enabled;
	}
}
