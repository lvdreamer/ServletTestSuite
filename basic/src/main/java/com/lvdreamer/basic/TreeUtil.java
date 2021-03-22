package com.lvdreamer.basic;

import com.google.common.collect.Sets;
import com.google.gson.Gson;

import java.util.*;

public class TreeUtil {
    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list     具有树形结构特点的集合
     * @param parentId 父节点ID
     * @return 树形结构集合 child menu vos
     */
    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list
     * @param parentId
     * @return
     */
    public static List<TreeNode> getChildTreeNodes(List<TreeNode> list, String parentId) {
        List<TreeNode> rootList = new ArrayList<>();
        //过滤已处理的节点
        Set<String> dealedSet = Sets.newHashSetWithExpectedSize(list.size());
        for (TreeNode node : list) {
            if (Objects.equals(node.getParentId(), parentId)) {
                getChildren(list, node, dealedSet);
                rootList.add(node);
            }
        }
        return rootList;
    }

    /**
     * 递归列表
     */
    private static void getChildren(List<TreeNode> list, TreeNode t, Set<String> dealedSet) {
        // 得到子节点列表
        List<TreeNode> childList = new ArrayList<>();
        t.setChildrenNodes(childList);
        list.stream()
                .filter(c -> !dealedSet.contains(c.getId()))
                .filter(c -> t.getId().equals(c.getParentId()))
                .forEach(c -> {
                    dealedSet.add(c.getId());
                    getChildren(list, c, dealedSet);
                    childList.add(c);
                });
        if (!childList.isEmpty()) {
            t.setHasChild(true);
        }
    }

    public static List<TreeNode> deepSort(List<TreeNode> list) {
        if (null == list || list.isEmpty()) {
            return list;
        }
        list.sort(Comparator.comparing(TreeNode::getId).reversed());
        for (TreeNode node : list) {
            if (null == node) {
                continue;
            }
            deepSort(node.getChildrenNodes());
        }
        return list;
    }

    static class TreeNode {
        private String id;
        private String name;
        private String parentId;
        private List<TreeNode> childrenNodes;
        private boolean hasChild = false;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public List<TreeNode> getChildrenNodes() {
            return childrenNodes;
        }

        public void setChildrenNodes(List<TreeNode> childrenNodes) {
            this.childrenNodes = childrenNodes;
        }

        public boolean isHasChild() {
            return hasChild;
        }

        public void setHasChild(boolean hasChild) {
            this.hasChild = hasChild;
        }
    }

    public static void main(String[] args) {
        List<TreeNode> treeNodes = new ArrayList<>();
        TreeNode treeNode = new TreeNode();
        treeNode.setId("1");
        treeNode.setName("1");
        treeNode.setParentId("0");
        treeNodes.add(treeNode);
        treeNode = new TreeNode();
        treeNode.setId("1-1");
        treeNode.setName("1-1");
        treeNode.setParentId("1");
        treeNodes.add(treeNode);
        treeNode = new TreeNode();
        treeNode.setId("1-2");
        treeNode.setName("1-2");
        treeNode.setParentId("1");
        treeNodes.add(treeNode);
        treeNode = new TreeNode();
        treeNode.setId("1-2-1");
        treeNode.setName("1-2-1");
        treeNode.setParentId("1-2");
        treeNodes.add(treeNode);
        treeNode = new TreeNode();
        treeNode.setId("1-2-2");
        treeNode.setName("1-2-2");
        treeNode.setParentId("1-2");
        treeNodes.add(treeNode);
        List<TreeNode> treeRes = TreeUtil.getChildTreeNodes(treeNodes, "1");
        System.out.println(new Gson().toJson(treeRes));
        TreeUtil.deepSort(treeRes);
        System.out.println(new Gson().toJson(treeRes));
    }
}
