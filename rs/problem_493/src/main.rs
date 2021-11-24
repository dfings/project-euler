use std::collections::HashMap;

const NUM_COLORS: usize = 7;
const NUM_PER_COLOR: i32 = 10;
const NUM_TO_PICK: i32 = 20;
const SUM_ALL_PICKED: i32 = NUM_COLORS as i32 * NUM_PER_COLOR - NUM_TO_PICK;

type Slots = [i32; NUM_COLORS];

struct Urn {
    slots: Slots,
}

impl Urn {
    fn colors_picked(&self) -> usize {
        self.slots
            .into_iter()
            .filter(|it| *it < NUM_PER_COLOR)
            .count()
    }

    fn all_picked(&self) -> bool {
        self.slots.iter().sum::<i32>() == SUM_ALL_PICKED
    }

    fn cache_key(&self) -> Slots {
        let mut key = self.slots.clone();
        key.sort();
        return key;
    }
}

#[derive(Copy, Clone)]
struct UrnStats {
    total_colors_picked: f64,
    total_picks: f64,
}

fn urn_stats(urn: &mut Urn, cache: &mut HashMap<Slots, UrnStats>) -> UrnStats {
    let key = urn.cache_key();
    match cache.get(&key) {
        Some(stats) => stats.clone(),
        None => {
            let mut stats = UrnStats {
                total_colors_picked: 0.0,
                total_picks: 0.0,
            };
            if urn.all_picked() {
                stats.total_colors_picked = urn.colors_picked() as f64;
                stats.total_picks = 1.0;
            } else {
                for color in 0..NUM_COLORS {
                    for _ in 0..urn.slots[color] {
                        urn.slots[color] -= 1;
                        let res = urn_stats(urn, cache);
                        stats.total_colors_picked += res.total_colors_picked;
                        stats.total_picks += res.total_picks;
                        urn.slots[color] += 1;
                    }
                }
            }
            cache.insert(key, stats);
            stats
        }
    }
}

fn main() {
    let mut urn = Urn {
        slots: [NUM_PER_COLOR; NUM_COLORS],
    };
    let mut cache = HashMap::<Slots, UrnStats>::new();
    let stats = urn_stats(&mut urn, &mut cache);
    println!("Total colors = {}", stats.total_colors_picked);
    println!("Total picks = {}", stats.total_picks);
    println!("Cache size = {}", cache.keys().len());
    println!(
        "Average = {}",
        stats.total_colors_picked / stats.total_picks
    );
}
