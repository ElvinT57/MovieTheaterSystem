/**
 * Purpose: Data Structure and Algorithms Lab 6 Problem 1
 * Status: Complete and thoroughly tested
 * Last update: 02/27/19
 * Submitted:  03/04/19
 * Comment: test suite and sample run attached
 *
 * @author: Elvin Torres
 * @version: 2019.03.04
 *
 */
public class QueueRA<T> implements QueueInterface<T> {
    //Data Fields
    protected int front, back, numItems;
    protected T[] items;

    public QueueRA(){
        //initial capacity of 5
        items = (T[]) new Object[5];
        //all equal to zero
        front = back = numItems = 0;
    }
    @Override
    public boolean isEmpty() {
        return (numItems == 0);
    }

    @Override
    public void enqueue(T newItem) throws QueueException {
        //resize if the capacity has been reach
        if(numItems == items.length)
            resize();
        items[back] = newItem;
        //calculate the new back
        back = (back+1) % items.length;
        //increment count
        numItems++;
    }

    @Override
    public T dequeue() throws QueueException {
        if(isEmpty())
            throw new QueueException("Queue is empty.");
        T result = items[front];
        items[front] = null;

        //calculate the new front
        front = (front+1) % items.length;
        numItems--;

        return result;
    }

    @Override
    public void dequeueAll() {
        //reinitialize
        front = back = numItems = 0;
        items = (T[]) new Object[5];
    }

    @Override
    public T peek() throws QueueException {
        if(isEmpty())
            throw new QueueException("Queue is empty.");
        //retrieve from the front
        return items[front];
    }

    protected void resize(){
        //create a new array with a larger capacity
        T[] temp = (T[])(new Object[items.length+(items.length/2)]);
        //iterate through the array
        for(int i = 0; i < numItems; i++){
            //store the enqueue into the larger array
            temp[i] = items[front];
            front = (front+1) % items.length;
        }

        //flip sides, front to zero and back to numItems
        front = 0;
        back = numItems;
        items = temp;   //GC take action
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        //iterator through the queue, from the front to the back
        for(int i = front; i != back; i = (i+1) % items.length){
            str.append(items[i].toString() + " ");
        }
        return str.toString();
    }
}
