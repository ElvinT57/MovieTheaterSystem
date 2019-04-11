public interface ParallelQueueInterface <T> {
    public boolean isEmpty();

    public void enqueue(T newItem);

    public T dequeue() throws QueueException;

    public void dequeueAll();

    public T peek();

    public String toString();
    //end ParallelQueueInterface
}
