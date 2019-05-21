/**
 *
 * Purpose: Data Structure and Algorithms Project
 * Status: Complete and thoroughly tested
 * Last update: 4/28/19
 * Submitted:  04/30/19
 * Comment: test suite and sample run attached
 *
 *
 * <p>@author: Christina Bannon, Elvin Torres</p>
 * <p>@version: 4.28.19</p>
 *
 * <h1>Round Robin Manner Parallel Queue</h1>
 * Representing a list of lines, using an array of Queues.
 * Items will be enqueued and dequeued in a round robin
 * manner.
 *
 * <h1>Functionality of Parallel Queue</h1>
 * <ul>
 *     <li>Keeps track of the last item that was enqueued.</li>
 *     <li>Keeps track of the next queue that will be enqueued and dequeued.</li>
 *     <li>Finds the smallest line for priority items.</li>
 *     <li>Enqueues and dequeues items in Round Robin manner</li>
 * </ul>
 *
 * <p>
 * The first queue in the array will be used for the express
 * line.
 * <p>
 * <code>
 *  {</br>
 *      {   EXPRESS LINE QUEUE  },</br>
 *      {   REG 1               },</br>
 *      {   REG 2               },</br>
 *      ...</br>
 *      {   REG n-1             }</br>
 *  }
 * </code>
 */
public class ParallelQueue<T> implements QueueInterface<T> {
    //Data structure for this ADT
    private QueueRA<T>[] queues;
    //Data fields
    private int currDQ;        //index of next dequeue
    private int currEQ;    //index of next enqueue
    private int lastEQ;     //keeps track of the last enqueue

    public ParallelQueue(int n) {
        //initialize the queues
        queues = new QueueRA[n];
        for (int i = 0; i < n; i++)
            queues[i] = new QueueRA<>();
        //set current to -1 to indicate it is our first call
        currDQ = -1;
        //set the current enqueue to 1 for the first line
        currEQ = lastEQ = 1;
    }

    @Override
    public boolean isEmpty() {
        boolean isEmpty = true; //assume it is empty
        for (int i = 0; i < queues.length; i++)
            //if one is not empty, then this parallel queue is not empty
            if (!queues[i].isEmpty())
                isEmpty = false;
        return isEmpty;
    }

    /**
     * Size of the parallel Queue, not the inner queue
     *
     * @return int - size
     */
    public int size() {
        return queues.length;
    }

    @Override
    public void enqueue(T newItem) {
        //if the customer has priority
        if (((Customer) newItem).isUnderAge()) {
            prioritize(newItem);
        } else {
            if (this.isEmpty()) {
                currEQ = 1;//if it is empty, start at the reg 1
                queues[currEQ++].enqueue(newItem);
            } else {
                //if the remainder is index 0 (Express) then pass to next index
                if (currEQ % queues.length == 0)
                    currEQ++;
                queues[(currEQ++) % queues.length].enqueue(newItem);
            }
            lastEQ = currEQ - 1; //update last enqueue
        }
    }

    @Override
    public T dequeue() throws QueueException {
        if (this.isEmpty())
            throw new QueueException("ParallelQueue is empty.");
        //continue the dequeue if not empty
        T object = null;
        while (object == null) {
            if (!queues[((currDQ) % queues.length)].isEmpty())
                //dequeue in round robin manner
                object = queues[((currDQ++) % queues.length)].dequeue();
            else
                currDQ++;   //increment to the next non empty queue
        }
        return object;
    }

    @Override
    public void dequeueAll() {
        //reinitialize the queues
        for (int i = 0; i < queues.length; i++)
            queues[i] = new QueueRA<>();
    }

    @Override
    /**
     * Returns the next item to be called from
     * the parallel queue.
     *
     */
    public T peek() {
        if (isEmpty())
            throw new QueueException("Queue is empty.");
        //retrieve the front of the current queue
        return queues[currDQ % queues.length].peek();
    }

    /**
     * Returns the index of the next
     * queue that will be dequeued.
     *
     * @return int - index
     */
    public int getCurrentDQ() {
        return currDQ;
    }

    public void setCurrentDQ(int currDQ) {
        this.currDQ = currDQ;
    }

    public int getLastEQ() {
        return lastEQ;
    }

    /**
     * Returns the size of the queue at the given
     * index.
     *
     * @return int Size
     */
    public int getSizeOf(int i) {
        return queues[i].numItems;
    }

    /**
     * Enqueue the new item into the priority queue. If
     * a smaller queue is found, enqueue to that queue.
     *
     * @param newItem new item to be added into the priority queue.
     */
    private void prioritize(T newItem) {
        //check for the smallest line compared to the express line
        int smallest = 0;   //assume the express is the smallest
        for (int i = 1; i < queues.length; i++) {
            if (queues[smallest].numItems > queues[i].numItems)
                smallest = i;   //new smallest line found!
        }
        //enqueue to the smallest queue
        queues[smallest].enqueue(newItem);
        //update last enqueue and currentEQ
        lastEQ = smallest;
        currEQ = smallest+1;
    }

    /**
     * Returns a references a queue in the
     * parallelqueue at the given index
     *
     * @param i index
     * @return
     */
    public QueueRA<T> getQueue(int i) {
        if (i < 0 || i > queues.length - 1)
            throw new QueueException("index is out of range!");
        return queues[i];
    }

    /**
     * Searches for the given name in the
     * parallel queue.
     *
     * @param name - item to search
     * @return true or false
     */
    public boolean contains(String name) {
        boolean found = false;
        for (int i = 0; i < queues.length; i++) {
            for (int j = 0; j < queues[i].numItems; j++) {
                Customer c = (Customer) queues[i].dequeue();
                if (c.getName().equals(name))
                    found = true;
                //enqueue it back, to get the original sequence
                queues[i].enqueue((T) c);
            }
            //if found, break the condition
            if (found)
                i = queues.length;
        }
        return found;
    }
}
