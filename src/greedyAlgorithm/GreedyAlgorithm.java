package greedyAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

//贪心算法——集合覆盖问题
//假设存在下面需要付费的广播台，以及广播台信号可以覆盖的地区。 如何选择最少的广播台，让所有的地区都可以接收到信号

public class GreedyAlgorithm {

	public static void main(String[] args) {
		//记录电台及其覆盖城市的集合
		Map<String,HashSet<String>> map=new HashMap<String, HashSet<String>>();
		HashSet<String> k1=new HashSet<String>();
		k1.add("北京");
		k1.add("上海");
		k1.add("天津");
		map.put("K1", k1);
		HashSet<String> k2=new HashSet<String>();
		k2.add("广州");
		k2.add("北京");
		k2.add("深圳");
		map.put("K2", k2);
		HashSet<String> k3=new HashSet<String>();
		k3.add("成都");
		k3.add("上海");
		k3.add("杭州");
		map.put("K3", k3);
		HashSet<String> k4=new HashSet<String>();
		k4.add("上海");
		k4.add("天津");
		map.put("K4", k4);
		HashSet<String> k5=new HashSet<String>();
		k5.add("杭州");
		k5.add("大连");
		map.put("K5", k5);
		//记录所有城市，不重复
		HashSet<String> allCity=new HashSet<String>();
		for(Map.Entry<String, HashSet<String>> citys:map.entrySet()) {
			allCity.addAll(citys.getValue());
		}
		System.out.println(allCity);
		//记录需要选择的广播站
		List<String> select=new ArrayList<String>();
		//临时变量
		HashSet<String> temp=null;
		//记录当前覆盖最广的广播站下标
		int index=0;
		//已经选择的广播站数量
		String maxK=null;
		while(allCity.size()>0) {
			//每次循环将选择的maxK重置
			maxK=null;
			//遍历取出广播站，并计算它与当前没有被覆盖城市的交集的城市个数
			//找出当前覆盖数量最大的广播站
			for(String k:map.keySet()) {
				temp=map.get(k);
				//temp中数据等于temp和allCity中共同数据，即取交集
				temp.retainAll(allCity);
				//判断交集最大的广播站
				if(temp.size()>0&&(maxK==null||map.get(maxK).size()<temp.size())) {
					maxK=k;
				}
			}
			//已经选择的广播站的数据集合
			//选择当前覆盖最多的
			select.add(maxK);
			//每次选择出覆盖最大的广播站，并删除其覆盖的城市，方便下次重新选择出新的覆盖最大的广播站
			allCity.removeAll(map.get(maxK));
			//记录每次选择后，剩余的城市
			System.out.println(allCity);
			//在总的广播站集合中删除已经选择的广播站，防止下次无效调用
			map.remove(maxK);
		}
		System.out.println("选择出的广播站："+select);
	}

}
