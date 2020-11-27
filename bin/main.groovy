#!/usr/bin/env groovy

class SegSieve {
// This function uses simple sieve of eratosthenes to
    // find primes upto sqrt(high)
    def simpleSieve(int limit, Vector prime)
    {
        // bound is square root of "high"
        int bound = (int)Math.sqrt(limit);

        boolean[] mark = new boolean[bound + 1];
        for (int i = 0; i <= bound; i++)
            mark[i] = true;

        for (int i = 2; i * i <= bound; i++) {
            if (mark[i] == true) {
                for (int j = i * i; j <= bound; j += i)
                    mark[i] = false;
            }
        }

        // adding primes to vector
        for (int i = 2; i <= bound; i++) {
            if (mark[i] == true)
                prime.add(i);
        }
    }

    // Finds all prime numbers in range low to high
    // using the primes we obtained from
    // simple sieve
    def segmentedSieve(int low, int high)
    {
        def prime = [] as Vector;
        simpleSieve(high, prime); // stores primes upto
                                  // sqrt(high) in prime

        boolean[] mark = new boolean[high - low + 1];
        for (int i = 0; i < mark.length; i++)
            mark[i] = true;

        for (int i = 0; i < prime.size(); i++) {
            // find minimum number in [low...high]
            // that is multiple of prime[i]
            int loLim = (low / prime.get(i)) * prime.get(i);
            // loLim += prime.get(i);
            if (loLim < low)
                loLim += prime.get(i);
            if (loLim == prime.get(i))
                loLim += prime.get(i);

            for (int j = loLim; j <= high;
                 j += prime.get(i)) {
                if (j != prime.get(i))
                    mark[j - low] = false;
            }
        }

        print "[OUTPUT] "
        // print all primes in [low...high]
        for (int i = low; i <= high; i++) {
            if (mark[i - low] == true)
                print(i + " ");
        }
    }

    // Driver Code
    def main()
    {
        int low = 10, high = 20;
        println "[INPUT] " + low + "," + high
        segmentedSieve(low, high);

    println ""
  }
}
new SegSieve().main()
