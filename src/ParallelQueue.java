public class ParallelQueue<T> implements QueueInterface<T> {
    //Data structure for this ADT
    private QueueRA<T>[] queues;
    //Data fields
    private int currDQ;        //index of next dequeue
    private int currEQ;    //index of next enqueue

    public ParallelQueue(int n) {
        //initialize the queues
        queues = new QueueRA[n];
        for (int i = 0; i < n; i++)
            queues[i] = new QueueRA<>();
        //set current to -1 to indicate it is our first call
        currDQ = -1;
        //set the current enqueue to 1 for the first line
        currEQ = 0;
    }

    @Override
    public boolean isEmpty() {
        boolean isEmpty = true; //assume it is empty
        for (int i = 0; i < queues.length; i++)
            if (queues[i].numItems > 0)
                isEmpty = false;
        return isEmpty;
    }

    public int size() {
        return queues.length;
    }

    @Override
    public void enqueue(T newItem) {
        //if the customer has priority
        if (((Customer) newItem).isUnderAge()) {
            prioritize(newItem);
        } else {
            System.out.println("lastEnqueue: " + currEQ);
            //if the remainder is index 0 (Express) then pass
            if (currEQ % queues.length == 0)
                currEQ++;
            queues[(currEQ++) % queues.length].enqueue(newItem);
        }
    }

    @Override
    public T dequeue() throws QueueException {
        if (isEmpty())
            throw new QueueException("ParallelQueue is empty.");

        T object = null;
        while (object == null) {
            if (!queues[currDQ].isEmpty())
                object = queues[((currDQ++)% queues.length)].dequeue();
            else
                currDQ++;
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
    public T peek() {
        if (isEmpty())
            throw new QueueException("Queue is empty.");
        //retrieve the front of the current queue
        return queues[currDQ % queues.length].peek();
    }

    public int getCurrentDQ() {
        return currDQ;
    }

    public void setCurrentDQ(int currDQ){
        this.currDQ = currDQ;
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

    private void prioritize(T newItem) {
        //check for the smallest line compared to the express line
        int smallest = 0;   //assume the express is the smallest
        for (int i = 1; i < queues.length; i++) {
            if (queues[smallest].numItems > queues[i].numItems) {
                smallest = i;   //new smallest line found!
            }
        }
        //enqueue to the smallest queue
        queues[smallest].enqueue(newItem);
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
}
