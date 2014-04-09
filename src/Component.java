/**
 * Created by Ben on 2/17/14.
 */
public class Component {
	private String UID = "NULL";
	private boolean enabled = true;

	protected void setUniqueID(String UID){
		this.UID = UID;
	}

	public String getUniqueID(){
		return UID;
	}

	public void setEnabled(Boolean enabled){
		this.enabled = enabled;
	}

	public boolean isEnabled(){
		return enabled;
	}
}
