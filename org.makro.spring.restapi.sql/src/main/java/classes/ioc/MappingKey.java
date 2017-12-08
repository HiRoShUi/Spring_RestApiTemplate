package classes.ioc;

import java.lang.reflect.Type;

public class MappingKey {
	
	
	  private Class _type;
	  private String _instanceName;
	  
	/// <summary>
    /// Type of the dependency
    /// </summary>
    public Class getType() {
	return _type; }
    public void setType(Class aiType){
    	_type = aiType;
    }

    /// <summary>
    /// Name of the instance (optional)
    /// </summary>
    public String getInstanceName(){
    	return _instanceName;
    }
    
    public void setInstanceName(String aiInstanceName){
    	_instanceName = aiInstanceName;
    }

    /// <summary>
    /// Creates a new instance of <see cref="MappingKey"/>
    /// </summary>
    /// <param name="type">Type of the dependency</param>
    /// <param name="instanceName">Name of the instance</param>
    /// <exception cref="ArgumentNullException">type</exception>
    public MappingKey(Class type, String instanceName) throws Exception
    {
        if (type == null)
            throw new Exception("type");

        setType(type);
        setInstanceName(instanceName);
    }
    
    

}
