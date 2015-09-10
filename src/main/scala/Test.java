import edu.illinois.wala.ipa.callgraph.FlexibleCallGraphBuilder;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.Config;
import com.ibm.wala.ipa.callgraph.propagation.cfa.nCFAContextSelector;
import com.ibm.wala.ipa.callgraph.ContextSelector;
import com.ibm.wala.ipa.callgraph.impl.ContextInsensitiveSelector;
import com.ibm.wala.ipa.slicer.SDG;
import com.ibm.wala.ipa.slicer.Slicer;

class Test {
  public static void main(String[] args) {
    Config config = ConfigFactory.load();
    FlexibleCallGraphBuilder pa = new FlexibleCallGraphBuilder(config) {
      @Override
      public ContextSelector cs() { 
        return new nCFAContextSelector(2, new ContextInsensitiveSelector()); 
      }
    };
    
    SDG sdg = new SDG(pa.cg(), pa.getPointerAnalysis(), Slicer.DataDependenceOptions.FULL, Slicer.ControlDependenceOptions.FULL);
    
    System.out.println(sdg);
  }
}
