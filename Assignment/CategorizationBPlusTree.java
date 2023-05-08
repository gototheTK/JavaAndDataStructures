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

    public Branch(Integer parent_idx, Integer child_id) {
        this.parent_idx = parent_idx;
        this.child_id = child_id;
    }

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

class Board implements Comparable<Board> {
    Branch branch;

    public Board(Branch branch) {
        this.branch = branch;
    }

    @Override
    public int compareTo(Board o) {
        // TODO Auto-generated method stub
        if (this.branch.parent_idx == o.branch.parent_idx && this.branch.child_id == o.branch.child_id) {
            return 0;
        } else if (this.branch.parent_idx > o.branch.parent_idx
                || this.branch.parent_idx == o.branch.parent_idx && this.branch.child_id > o.branch.child_id) {
            return 1;
        }

        return -1;
    }
}

public class CategorizationBPlusTree {

    private Integer size;
    private Integer sequence = 0;
    private BranchCategory root;
    private LeafCategory firstLeaf;

    public CategorizationBPlusTree(Integer size) {
        this.size = size;
    }

    private LeafCategory findLeafNode(Branch branch) {

        return findLeafNode(this.root, branch);

    }

    private void handleDeficiency(BranchCategory branchCategory) {

        BranchCategory sibling;
        BranchCategory parent;

        if (this.root == branchCategory) {

        }

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

    private Integer findIndexOfPointer(List<Category> list, LeafCategory leaf) {
        Integer index = 0;

        for (Category category : list) {

            if (leaf == category) {
                break;
            }
            index++;
        }
        return index;
    }

    public void insert(Branch branch) {

        if (isEmpty()) {
            this.firstLeaf = new LeafCategory(branch);
        } else {
            LeafCategory leaf = (null == this.root) ? this.firstLeaf : findLeafNode(branch);

            if (!leaf.insert(new Board(branch))) {

                leaf.boards.add(new Board(branch));
                Collections.sort(leaf.boards);
                List<Board> halfBoard = splitBoards(leaf);

                if (null == leaf.parent) {

                    List<Branch> parent_branches = new ArrayList<>(size * 2);
                    parent_branches.add(halfBoard.get(0).branch);
                    BranchCategory parent = new BranchCategory(parent_branches);
                    leaf.parent = parent;
                    parent.child.add(leaf);

                } else {
                    Branch newParentBranch = halfBoard.get(0).branch;
                    leaf.parent.branches.add(branch);
                    Collections.sort(leaf.parent.branches);
                }

                LeafCategory newLeaf = new LeafCategory(halfBoard, leaf.parent);

                Integer pointerIndex = leaf.parent.child.indexOf(leaf) + 1;
                leaf.parent.child.add(pointerIndex, newLeaf);

                newLeaf.right = leaf.right;
                if (null != newLeaf.right) {
                    newLeaf.right.left = newLeaf;
                }
                leaf.right = newLeaf;
                newLeaf.left = leaf;

                if (null == this.root) {
                    this.root = leaf.parent;
                } else {
                    BranchCategory branchCategory = leaf.parent;
                    while (null != branchCategory) {

                        if (branchCategory.isOverfull()) {
                            splitBranchCategory(branchCategory);
                        } else {
                            break;
                        }

                        branchCategory = branchCategory.parent;

                    }
                }

            }

        }

    }

    private void splitBranchCategory(BranchCategory branchCategory) {
        BranchCategory parent = branchCategory.parent;
        Branch newParentBranch = branchCategory.branches.get(size);
        /**
         * 반으로 쪼개기
         */
        List<Branch> halfBranches = branchCategory.branches.subList(size, branchCategory.branches.size());
        List<Category> halfCategories = branchCategory.child.subList(0, size);
        branchCategory.child = branchCategory.child.subList(size, branchCategory.child.size());

        /** 새로운 형제의 자식들 */
        BranchCategory sibling = new BranchCategory(size, halfBranches, halfCategories);
        for (Category category : halfCategories) {
            category.parent = sibling;
        }

        /**
         * 
         */
        sibling.rightSibling = branchCategory.rightSibling;
        if (null != sibling.rightSibling) {
            sibling.rightSibling.leftSibling = sibling;
        }
        branchCategory.rightSibling = sibling;
        sibling.leftSibling = branchCategory;

        if (null == parent) {
            BranchCategory newRoot = new BranchCategory(size, halfBranches, halfCategories);
            newRoot.child.add(branchCategory);
            newRoot.child.add(sibling);
            this.root = newRoot;

            branchCategory.parent = newRoot;
            sibling.parent = newRoot;
        } else {

            parent.branches.add(newParentBranch);
            Collections.sort(parent.branches);

            Integer index = parent.child.indexOf(branchCategory) + 1;
            parent.child.add(index, sibling);
            sibling.parent = parent;

        }

    }

    private List<Board> splitBoards(LeafCategory leaf) {

        List<Board> half = leaf.boards.subList(size, leaf.boards.size());
        leaf.boards = leaf.boards.subList(0, size);

        return half;

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

        public BranchCategory(List<Branch> branches) {
            this.maxSize = size * 2;
            this.minSize = size;
            this.branches = branches;
            this.child = new ArrayList<>();
        }

        public BranchCategory(Integer size, List<Branch> branches, List<Category> child) {
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

        public Boolean insert(Board board) {

            if (this.isfull()) {
                this.boards.add(board);
                Collections.sort(boards);
                return true;
            }

            return false;
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

    public void search() {

        LeafCategory leaf = firstLeaf;

        while (null != leaf) {

            System.out.println();

        }

    }

    /** 실행 */

    public static void main(String[] args) {

        CategorizationBPlusTree test = new CategorizationBPlusTree(10);

        test.insert(new Branch(1, 1));

    }

}
