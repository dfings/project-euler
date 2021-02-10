#!/usr/bin/env kotlin

println((1..999).filter { it % 3 == 0 || it % 5 == 0}.sum())
