import java.util.HashMap;
import java.util.Map;

/**
 * @author tianyh
 */
public class IdAndSignature {
    private HashMap<String,String> idAndSignature;

    public HashMap<String, String> getIdAndSignature() {
        return idAndSignature;
    }

    public void setIdAndSignature(HashMap<String, String> idAndSignature) {
        this.idAndSignature = idAndSignature;
    }

    public IdAndSignature(){}

    public String findIdBySignature(String signature){
        for (Map.Entry<String, String> stringStringEntry : this.idAndSignature.entrySet()) {
            if (signature.equals(stringStringEntry.getValue())) {
                return stringStringEntry.getKey();
            }
        }
        System.out.println("There is no tweet has this signature:"+signature);
        return null;
    }
}
