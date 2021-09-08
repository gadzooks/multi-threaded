# Basic Multi-threaded LRU cache implementation

## Features : 
1. Least recently used element is discarded if cache is full
2. Use Abstract base class and factory pattern to allow creating new Cache
   classes easily
3. Use *synchronized* and *volatile* to guarantee mutual exclusion and sharing
   data between threads
