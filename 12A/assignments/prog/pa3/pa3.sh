#/bin/bash

javac GCD.java
echo "If nothing between '=' signs, then test is passed::"
echo "Test 1:"
echo "=========="
java GCD < in1.txt > out1.txt
diff -bBw out1.txt model-out1.txt > diff1.txt
cat diff1.txt
echo "=========="
echo "Test 2:"
echo "=========="
java GCD < in2.txt > out2.txt
diff -bBw out2.txt model-out2.txt > diff2.txt
cat diff2.txt
echo "=========="
echo "Test 3:"
echo "=========="
java GCD < in3.txt > out3.txt
diff -bBw out3.txt model-out3.txt > diff3.txt
cat diff3.txt
echo "Test 4:"
echo "=========="
java GCD < in4.txt > out4.txt
diff -bBw out4.txt model-out4.txt > diff4.txt
cat diff4.txt
echo "=========="
echo "Test 5:"
echo "=========="
java GCD < in5.txt > out5.txt
diff -bBw out5.txt model-out5.txt > diff5.txt
cat diff5.txt
echo "=========="
echo "Test 6:"
echo "=========="
java GCD < in6.txt > out6.txt
diff -bBw out6.txt model-out6.txt > diff6.txt
cat diff6.txt
echo "=========="
rm *.class
