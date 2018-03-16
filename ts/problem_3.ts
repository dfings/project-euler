#!/usr/bin/env ts-node

import { prime_factors } from "./factor";

let factors = prime_factors(600851475143);
console.log(factors[factors.length - 1]);
