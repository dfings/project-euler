#!/usr/bin/env ts-node

import { prime_factors } from "./factor.ts";

let factors = prime_factors(600851475143);
console.log(factors[factors.length - 1]);
