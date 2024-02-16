import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TagUtility {
	
	 /**
	  * Returns a List of all the values in an Enum (mainly for Tags)
	  * @param <E>
	  * @param enumClass
	  * @return a list of all the values in an Enum
	  */
    public static <E extends Enum<E> & Tag> List<E> getAllEnumValues(Class<E> enumClass) {
        return Arrays.asList(enumClass.getEnumConstants());
    }

    /**
     * Returns a String list of all the values in an Enum as their displayName
     * @param <E>
     * @param enumClass
     * @return a String list of all the values in an Enum as their displayName
     */
    public static <E extends Enum<E> & Tag> List<String> getAllDisplayNames(Class<E> enumClass) {
        List<String> displayNames = new ArrayList<>();
        for (E enumConstant : enumClass.getEnumConstants()) {
            displayNames.add(enumConstant.getDisplayName());
        }
        return displayNames;
    }
}