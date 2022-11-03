import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class StringFinder {
    public Character[] findNonRepeatingCharacters(String string) {
        var list = new ArrayList<Character>();
        var hashMap = new HashMap<Character, Integer>();
        for (var character : string.toCharArray()) {
            var item = hashMap.get(character);
            hashMap.put(character, item == null ? 0 : item + 1);
        }
        hashMap.forEach((key, value) -> {
            if (value == 0) list.add(key);
        });
        return list.toArray(new Character[0]);
    }

    public Character[] findRepeatingCharacters(String string) {
        var resultSet = new HashSet<Character>();
        var characterSet = new HashSet<Character>();
        for (var character : string.toCharArray())
            if (!characterSet.add(character))
                resultSet.add(character);
        return resultSet.toArray(new Character[0]);
    }
}
