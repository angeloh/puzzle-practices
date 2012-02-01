package BFS;

import BFS.pTwoChildNode;

public abstract class pGenericBinaryTree{
    private pTwoChildNode root;

    protected pTwoChildNode getRoot(){
        return root;
    }
    protected void setRoot(pTwoChildNode r){
        root = r;
    }
    public pGenericBinaryTree(){
        setRoot(null);
    }
    public pGenericBinaryTree(Object o){
        setRoot(new pTwoChildNode(o));
    }
    public boolean isEmpty(){
        return getRoot() == null;
    }
    public Object getData(){
        if(!isEmpty())
            return getRoot().getData();
        return null;
    }
    public pTwoChildNode getLeft(){
        if(!isEmpty())
            return getRoot().getLeft();
        return null;
    }
    public pTwoChildNode getRight(){
        if(!isEmpty())
            return getRoot().getRight();
        return null;
    }
    public void setData(Object o){
        if(!isEmpty())
            getRoot().setData(o);
    }
    public void insertLeft(pTwoChildNode p,Object o){
        if((p != null) && (p.getLeft() == null))
            p.setLeft(new pTwoChildNode(o));
    }
    public void insertRight(pTwoChildNode p,Object o){
        if((p != null) && (p.getRight() == null))
            p.setRight(new pTwoChildNode(o));
    }
    public void print(int mode){
        if(mode == 1) pretrav();
        else if(mode == 2) intrav();
        else if(mode == 3) postrav();
    }
    public void pretrav(){
        pretrav(getRoot());
    }
    protected void pretrav(pTwoChildNode p){
        if(p == null)
            return;
        System.out.print(p.getData()+" ");
        pretrav(p.getLeft());
        pretrav(p.getRight());
    }
    public void intrav(){
        intrav(getRoot());
    }
    protected void intrav(pTwoChildNode p){
        if(p == null)
            return;
        intrav(p.getLeft());
        System.out.print(p.getData()+" ");
        intrav(p.getRight());
    }
    public void postrav(){
        postrav(getRoot());
    }
    protected void postrav(pTwoChildNode p){
        if(p == null)
            return;
        postrav(p.getLeft());
        postrav(p.getRight());
        System.out.print(p.getData()+" ");
    }
}