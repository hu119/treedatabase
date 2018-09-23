package treedatabase;

import com.treebase.database.treeDatabase;
import com.treebase.database.config.myConfig;
import com.treebase.database.config.myConfigFactory;
import com.treebase.database.datasource.mysql.myNodeLib;
import com.treebase.database.define.field_attrib_def;
import com.treebase.database.define.node_attrib_def;
import com.treebase.database.main.node;



public class dbTest {

	public static void main(String[] args){
	
		node nd;
		try {
			String filename="C:\\Users\\Administrator\\Workspaces\\MyEclipse 2017 CI\\treedatabase\\src\\main\\resources\\tdc.xml";
			treeDatabase td=new treeDatabase();
			td.startup(filename);
			
			myNodeLib tr=new myNodeLib();
			nd=new node();
			nd.pmark="ACB766C6067F046105F8B1263B9E5172";
			nd.name="test";
			nd.node_type=node_attrib_def.field;
			nd.node_attrib=field_attrib_def.myenchar;
			nd.descs="testok";
			tr.addRec(nd);
			
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
