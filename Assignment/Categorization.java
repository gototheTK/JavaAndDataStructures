import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.omg.CORBA.UNKNOWN;

/**
 * Categories
 */

enum Type {

    MAN(1, "남자"),
    WOMAN(2, "여자"),
    ///
    EXO(3, "엑소"),
    BTS(4, "방탄소년단"),
    BLACKPINK(5, "블랙핑크"),
    ///
    NOTICE(6, "공지사항"),
    UNKNOWN(7, "익명게시판");

    Integer id;
    String name;

    Type(Integer id, String name) {

        this.id = id;
        this.name = name;
    }

}

class Branch implements Comparable<Branch> {
    Integer parent_idx;
    Integer child_id;

    @Override
    public int compareTo(Branch o) {
        // TODO Auto-generated method stub

        if (this.parent_idx == o.parent_idx && this.child_id == o.child_id) {
            return 0;
        } else if (this.parent_idx > o.parent_idx || this.parent_idx == o.parent_idx && this.child_id > o.child_id) {
            return 1;
        }

        return -1;

    }
}

class Board {
    Branch branch;

    public Board(Branch branch) {
        this.branch = branch;
    }
}

public class Categorization {

    private Integer size;
    private Integer sequence = 0;
    private BranchCategory root;
    private LeafCategory firstLeaf;

    public Categorization(Integer size) {
        this.size = size;
    }

    private LeafCategory findLeafNode(Branch branch) {

        return findLeafNode(this.root, branch);

    }

    private LeafCategory findLeafNode(BranchCategory branchCategory, Branch branch) {

        List<Branch> list = branchCategory.branches;
        Integer index = 0;

        for (Branch temp : list) {
            if (0 > branch.compareTo(temp)) {
                break;
            }
            index++;
        }

        Category child = branchCategory.child.get(index);

        if (child instanceof LeafCategory) {
            return (LeafCategory) child;
        }

        return findLeafNode((BranchCategory) child, branch);

    }

    public void insert(Branch branch) {

        if (isEmpty()) {
            this.firstLeaf = new LeafCategory(branch);
        } else {
            LeafCategory leaf = (null == this.root) ? this.firstLeaf : findLeafNode(branch);

            leaf.insert(new Board(branch));

            if (null == leaf.parent) {

            }

        }

    }

    private Boolean isEmpty() {
        return null == firstLeaf;
    }

    class Category {
        BranchCategory parent;
    }

    class BranchCategory extends Category {
        Integer maxSize;
        Integer minSize;
        BranchCategory leftSibling;
        BranchCategory rightSibling;
        List<Branch> branches;
        List<Category> child;

        public BranchCategory(List<Branch> branches, Boolean isLeaf) {
            this.maxSize = size * 2;
            this.minSize = size;
            this.branches = branches;
            this.child = new ArrayList<>();
        }

        public BranchCategory(Integer size, List<Branch> branches, List<Category> child, Boolean isLeaf) {
            this.maxSize = size * 2;
            this.minSize = size;
            this.branches = branches;
            this.child = child;
        }

        private Category findChildCategory(Category category) {
            for (Category child : this.child) {
                return child;
            }
            return null;
        }

        private void insertCategory(Category category) {
            this.child.add(category);
        }

        private void prependCategory(Category category) {
            this.child.add(0, category);
        }

        private void removeCategory(Category category) {
            this.child.remove(category);
        }

        private Boolean isDeficient() {
            return this.child.size() < this.minSize;
        }

        private Boolean isLendable() {
            return this.child.size() > minSize;
        }

        private Boolean isMergeable() {
            return this.child.size() == this.minSize;
        }

        private Boolean isOverfull() {
            return this.child.size() > this.maxSize;
        }

    }

    class LeafCategory extends Category {

        Integer number;
        Integer maxSize;
        Integer minSize;
        LeafCategory left;
        LeafCategory right;
        List<Board> boards;

        public void delete(Branch branch) {
        }

        public void insert(Board board) {
            boards.add(board);
        }

        private Boolean isDeficient() {
            return this.boards.size() < this.minSize;
        }

        private Boolean isLendable() {
            return this.boards.size() > minSize;
        }

        private Boolean isfull() {
            return this.boards.size() == this.maxSize;
        }

        private Boolean isMergeable() {
            return this.boards.size() == this.minSize;
        }

        public LeafCategory(Branch branch) {
            this.maxSize = (size * 2) - 1;
            this.minSize = size - 1;
            this.boards = new ArrayList<>(100);
            this.boards.add(new Board(branch));
        }

        public LeafCategory(List<Board> boards, BranchCategory parent) {
            this.maxSize = (size * 2) - 1;
            this.minSize = size - 1;
            this.boards = boards;
            this.parent = parent;
        }

    }

    /** 실행 */

    public static void main(String[] args) {

    }

}
