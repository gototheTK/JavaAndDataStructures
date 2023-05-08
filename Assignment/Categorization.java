import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Categories
 */

public class Categorization {

    class Category {

        List<String> parent_idx;
        List<String> child_id;
        Category subcategory;

        public Category() {
            this.parent_idx = new ArrayList<>(100);
            this.child_id = new ArrayList<>(100);
        }

    }

    Category root;

    private final String unknown = "익명게시판";
    private final String notice = "공지사항";
    private Integer unknownNumber;

    public Categorization() {
        this.root = new Category();
    }

    public void add(String parent) {

        root.parent_idx.add(parent);
        root.child_id.add(parent);

    }

    public void add(String parent, String child) {

        Category pointer = root;
        Integer parentIndex = -1;

        while (null != pointer) {

            parentIndex = pointer.child_id.indexOf(parent);
            if (-1 < parentIndex) {
                break;
            }
            pointer = pointer.subcategory;
        }

        if (null == pointer || 0 > parentIndex) {
            return;
        } else if (null == pointer.subcategory) {
            pointer.subcategory = new Category();
        }

        Integer lastIndex = pointer.subcategory.parent_idx.lastIndexOf(parent);

        if (lastIndex > -1) {
            pointer.subcategory.parent_idx.add(lastIndex + 1, parent);
            pointer.subcategory.child_id.add(lastIndex + 1, child);
        } else {
            pointer.subcategory.parent_idx.add(parent);
            pointer.subcategory.child_id.add(child);
        }

    }

    public Category search(String parent) {
        Category subcategory = new Category();
        Category pointer = root;
        Category subPoint = subcategory;

        LinkedList<String> queue = new LinkedList<>();
        String name;
        Integer fromIndex = -1;
        Integer toIndex = -1;

        while (null != pointer) {

            if (-1 < pointer.child_id.indexOf(parent)) {
                subPoint.parent_idx.add(parent);
                subPoint.child_id.add(parent);
                subPoint.subcategory = new Category();
                subPoint = subPoint.subcategory;
                break;
            }
            pointer = pointer.subcategory;

        }

        pointer = pointer.subcategory;

        queue.add(parent);

        while (null != pointer) {

            name = queue.poll();

            fromIndex = pointer.parent_idx.indexOf(name);
            toIndex = pointer.parent_idx.lastIndexOf(name);

            if (-1 < fromIndex) {
                subPoint.parent_idx.addAll(pointer.parent_idx.subList(fromIndex, toIndex + 1));
                subPoint.child_id.addAll(pointer.child_id.subList(fromIndex, toIndex + 1));

            }

            if (queue.isEmpty()) {

                for (String subName : subPoint.child_id) {
                    queue.add(subName);
                }

                pointer = pointer.subcategory;
                subPoint.subcategory = new Category();
                subPoint = subPoint.subcategory;

            }

        }

        if (1 > subcategory.child_id.size()) {
            System.out.println("카테고리가 존재하지 않습니다.");
            return null;
        }

        return subcategory;

    }

    public String allToJson() {

        return this.toJson(root);
    }

    public String toJson(Category category) {

        System.out.println("--------------------");

        Category pointer = category;
        Map<String, List<String>> map = new HashMap<>(1000);
        Integer height = 1;

        for (String name : category.child_id) {
            map.put(name, new ArrayList<>(1000));
            map.get(name).add(name);
        }

        pointer = pointer.subcategory;

        while (null != pointer) {

            Integer index = 0;
            for (String parent : pointer.parent_idx) {

                List<String> temp = new ArrayList<>();
                temp.addAll(map.get(parent));

                if (pointer.child_id.get(index).equals(notice) || pointer.child_id.get(index).equals(unknown)) {

                    temp.add(pointer.child_id.get(index));
                    map.put(parent, temp);
                } else {
                    temp.add(pointer.child_id.get(index));
                    if (temp.contains(notice)) {
                        temp.remove(notice);

                    }
                    if (temp.contains(unknown)) {
                        temp.remove(unknown);
                    }

                    map.put(pointer.child_id.get(index), temp);
                }

                index++;

            }

            pointer = pointer.subcategory;
            height++;
        }

        System.out.println("--------------------");

        List<String> results = new ArrayList<>(1000);

        for (List<String> temp : map.values()) {
            results.add(temp.toString());
        }
        Collections.sort(results);

        for (String line : results) {
            System.out.println(line);
        }

        return "dd";

    }

    /** 실행 */

    public static void main(String[] args) {

        Categorization test = new Categorization();

        test.add("남자");
        test.add("여자");
        test.add("남자", "엑소");
        test.add("남자", "방탄소년단");
        test.add("여자", "블랙핑크");
        test.add("엑소", "공지사항");
        test.add("엑소", "첸");
        test.add("엑소", "백현");
        test.add("엑소", "시우민");
        test.add("방탄소년단", "공지사항");
        test.add("방탄소년단", "익명게시판");
        test.add("방탄소년단", "뷔");
        test.add("블랙핑크", "공지사항");
        test.add("블랙핑크", "익명게시판");
        test.add("블랙핑크", "로제");

        Category range = test.search("엑소");
        test.allToJson();
        test.toJson(range);

    }

}
