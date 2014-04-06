/**
 * Created by Ben on 2/17/14.
 */
public final class Component {
	private static String UID;
	private boolean enabled = false;

	protected void setUniqueID(String UID){
		this.UID = UID;
	}

	public static String getUniqueID(){
		return UID;
	}

	public void setEnabled(Boolean enabled){
		this.enabled = enabled;
	}

	public boolean isEnabled(){
		return enabled;
	}
}
