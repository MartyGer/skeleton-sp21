package tester;
import static org.junit.Assert.*;
import org.junit.Test;
import student.StudentArrayDeque;
import edu.princeton.cs.algs4.StdRandom;

public class TestArrayDequeEC {
    @Test
    public void TestStudentArray(){
        StudentArrayDeque<Integer> input = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();

        int[] RandomNumber = new int[20];
        int i = 0;
        while (i < 20)
        {
            RandomNumber[i] = i;
            i++;
        }

        i = 0;
        while (i < 100)
        {
            Integer numTemp = StdRandom.discrete(RandomNumber);
            if (numTemp >= 10) {
                input.addLast(numTemp);
                solution.addLast(numTemp);
            }
            /*else {
                numTemp = StdRandom.discrete(RandomNumber);
                input.addFirst(numTemp);
                solution.addFirst(numTemp);
            }*/
            i++;
        }

       /* input.removeFirst();
        solution.removeFirst();
        input.removeLast();
        solution.removeLast();
        input.removeFirst();
        solution.removeFirst();
        input.removeFirst();
        solution.removeFirst();
*/
        i = 0;
        Integer[] solutionTest = new Integer[solution.size()];
        Integer[] inputTest = new Integer[input.size()];
        int size = input.size();
        while (i < size)
        {
            if (i < input.size()) {
                solutionTest[i] = solution.get(i);
                inputTest[i] = input.get(i);
                assertArrayEquals(solutionTest, inputTest);
            }
            input.removeFirst();
            solution.removeFirst();

            i++;
        }
         input.printDeque();
        System.out.println(solution.get(1));
         System.out.println(input.get(1));
        assertArrayEquals(solutionTest, inputTest);
    }
    public static void main(String[] args) {
        TestArrayDequeEC t = new TestArrayDequeEC();
        t.TestStudentArray();
    }
}
