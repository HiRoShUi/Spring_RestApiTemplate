package classes.sql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class TokenMap {
	
	private static Map<UUID, IConnector> _map;
	
	public static boolean isInitialized(){
		if(_map == null)
			return false;
		return true;
	}
	
	public static void init(){
		_map = new HashMap<UUID, IConnector>();
	}
	
	public static void add(UUID aiKey, IConnector aiConnector){
		_map.put(aiKey,aiConnector);
	}
	
	public static IConnector findSqlConnector(UUID aiKey){
		return _map.get(aiKey);
	}
	
	public static void remove(UUID aiKey){
		_map.remove(aiKey);
	}

	public static List<UUID> getAllTokens() {
		List<UUID> aoList = new ArrayList<UUID>();
		aoList.addAll(_map.keySet());
		return aoList;
	}

	public static Boolean isIDKnown(UUID id) {
		return _map.get(id) != null;
	}
}
