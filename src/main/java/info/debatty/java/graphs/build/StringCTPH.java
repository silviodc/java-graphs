/*
 * The MIT License
 *
 * Copyright 2015 Thibault Debatty.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package info.debatty.java.graphs.build;

import info.debatty.java.graphs.Node;
import info.debatty.java.spamsum.ESSum;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thibault Debatty
 */
public class StringCTPH extends PartitioningGraphBuilder<String> {

    @Override
    protected List<Node<String>>[][] _partition(List<Node<String>> nodes) {
        int n = nodes.size();
        ESSum ess = new ESSum(n_stages, n_partitions, 1);
        ArrayList<Node<String>>[][] partitions = new ArrayList[n_stages][n_partitions];
        for (Node node : nodes) {
            int[] signature = ess.HashString((String) node.value);
            //System.out.println(hash);
            
            // Stage
            for (int s = 0; s < n_stages; s++) {
                int bucket = signature[s];
                
                if (partitions[s][bucket] == null) {
                    partitions[s][bucket] = new ArrayList<Node<String>>();
                }
                partitions[s][bucket].add(node);
            }
        }
        
        return partitions;
    }
    
}