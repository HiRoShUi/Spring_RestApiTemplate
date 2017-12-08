package classes.ioc;

import java.lang.reflect.Type;
import java.util.Hashtable;
import java.util.function.Function;

public class IocContainer<T, R> {

	private Hashtable<MappingKey,Function<T,R>> _mapping;
	
	public IocContainer()
	{
		_mapping = new Hashtable<MappingKey,Function<T,R>>();
	}
	

    public boolean IsRegistered(Class type, String instanceName) throws Exception
    {
        if (type == null)
            throw new Exception("type");

        MappingKey key = new MappingKey(type, instanceName);
        return _mapping.containsKey(key);
    }
    
    public void Register(Class type, Function<T,R> createInstanceDelegate, String instanceName) throws Exception
    {
        if (type == null)
            throw new Exception("type");

        if (createInstanceDelegate == null)
            throw new Exception("createInstanceDelegate");

        MappingKey key = new MappingKey(type, instanceName);

        if (_mapping.containsKey(key))
        {
            throw new Exception("The requested mapping already exists!");
        }

        _mapping.put(key, createInstanceDelegate);
    }
    
    
    public Object Resolve(Class type, String instanceName) throws Exception
    {
        MappingKey key = new MappingKey(type, instanceName);
        Function<T,R> value = _mapping.get(key);
        
        if (value != null)
            return value;       
        return null;
    }
	
}
