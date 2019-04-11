public class ParallelQueue<T> implements ParallelQueueInterface<T> {
    //Data structure for this ADT
    private QueueRA<T>[] queues;
    //Data fields
    private int current;
    private int lastEnqueue;    //index of last enqueue

    public ParallelQueue(int n) {
        //initialize the queues
        queues = new QueueRA[n];
        for (int i = 0; i < n; i++)
            queues[i] = new QueueRA<>();
        //set current to -1 to indicate it is our first call
        current = -1;
        //set the last enqueue to 1 for the first line
        lastEnqueue = 0;
    }

    @Override
    public boolean isEmpty() {
        boolean isEmpty = true; //assume it is empty
        for (int i = 0; i < queues.length; i++)
            if (queues[i].numItems > 0)
                isEmpty = false;
        return isEmpty;
    }

    @Override
    public void enqueue(T newItem) {
        //if the customer has priority
        if (((Customer) newItem).isUnderAge())
            prioritize(newItem);
        else
            //if the remainder is index 0 (Express) then pass
            if (lastEnqueue % queues.length == 0)
                lastEnqueue++;
        queues[(lastEnqueue++) % queues.length].enqueue(newItem);
    }

    @Override
    public T dequeue() throws QueueException {
        if (isEmpty())
            throw new QueueException("ParallelQueue is empty.");

        T object = null;
        while (object == null) {
            if (!queues[current].isEmpty())
                object = queues[((current) % queues.length)].dequeue();
            else
                current++;
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
        return queues[current % queues.length].peek();
    }

    public int getCurrent() {
        return current;
    }

    private void prioritize(T newItem) {
        //check for the smallest line compared to the express line
        int smallest = 0;   //assume the express is the smallest
        for (int i = 1; i < queues.length; i++) {
            if(queues[smallest].numItems < queues[i].numItems)
                smallest = i;   //new smallest line found!
        }
        //enqueue to the smallest queue
        queues[smallest].enqueue(newItem);
    }
}