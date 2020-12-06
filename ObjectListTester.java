public class ObjectListTester
{
  public static void main(String[] args)
  {

    ObjectListInterface<Integer> t1 = new ObjectList<>();
    t1.append(1);
    t1.append(2);
    System.out.println(t1.getCurrent() + "   Expected: 2");

    t1.replace(3);
    System.out.println(t1.getCurrent() + "   Expected: 3");

    System.out.println(t1.getFirst() +   "   Expected: 1");
    System.out.println(t1.getLast() +    "   Expected: 3");
    System.out.println(t1.getPrevious() +    "   Expected: 1");

    System.out.println(t1.getCurrentPosition() + "    Expected: 0");
    System.out.println(t1.getNext() +    "   Expected: 3");
    System.out.println(t1.getCurrentPosition() + "    Expected: 1");

    t1.insert(2);
    t1.getFirst();
    System.out.println(t1.getNext() +    "   Expected: 2");
    System.out.println(t1.getCurrentPosition() + "    Expected: 1");

    t1.getNext();
    System.out.println(t1.getCurrentPosition() + "    Expected: 2");

    t1.remove();    
    System.out.println(t1.getCurrent() + "   Expected: 2");






  }
}
