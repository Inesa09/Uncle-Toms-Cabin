package com.queue.q.LinkedQueuesRealisation;

import com.queue.q.Request;

public class LinkedQueue {
    private LinkedQueueNode headOfList;

    public Request getRequest(){
        Request requestToReturn = headOfList.getRequest();
        if(headOfList.getNodeNext() == null){
            headOfList = null;
        }
        else {
            headOfList = headOfList.getNodeNext();
        }
        return requestToReturn;
    }

    public boolean isEmpty(){
        return headOfList == null;
    }

    public void setRequest(Request request){
        LinkedQueueNode nodeToSet = new LinkedQueueNode();
        nodeToSet.setRequest(request);
        if(headOfList==null){
            headOfList = nodeToSet;
        }
        else {
            if(CompareRequest(request, headOfList.getRequest())){
                nodeToSet.setNodeNext(headOfList);
                headOfList.setNodeLast(nodeToSet);
                headOfList = nodeToSet;
            }
            else {
                LinkedQueueNode nodeToSearchInQueue = headOfList;

                while(nodeToSearchInQueue.getNodeNext() != null && !CompareRequest(request, nodeToSearchInQueue.getRequest())){
                    nodeToSearchInQueue = nodeToSearchInQueue.getNodeNext();
                }

                if(!CompareRequest(request, nodeToSearchInQueue.getRequest())){
                    nodeToSearchInQueue.setNodeNext(nodeToSet);
                    nodeToSet.setNodeLast(nodeToSearchInQueue);
                }
                else {
                    nodeToSet.setNodeLast(nodeToSearchInQueue.getNodeLast());
                    nodeToSearchInQueue.getNodeLast().setNodeNext(nodeToSet);
                    nodeToSearchInQueue.setNodeLast(nodeToSet);
                    nodeToSet.setNodeNext(nodeToSearchInQueue);
                }
            }

        }
    }


    private boolean CompareRequest(Request req1 , Request req2){
        if(req1.getPriority()>req2.getPriority()){
            return true;
        }
        else if(req1.getPriority() == req2.getPriority() && req1.getIdToDataBase()<req2.getIdToDataBase()){
            return true;
        }
        else {
            return false;
        }
    }
}
