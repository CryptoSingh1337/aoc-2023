use std::{cmp, collections::HashMap, collections::HashSet, fs::File, io::BufRead, io::BufReader};

fn main() {
    let file = File::open("input.txt").expect("File not found");
    let reader = BufReader::new(file);

    let mut input: Vec<String> = Vec::new();
    for line in reader.lines() {
        match line {
            Ok(line) => {
                let splitted = line.split(":").nth(1);
                match splitted {
                    Some(data) => input.push(data.trim().to_owned()),
                    None => (),
                }
            }
            Err(_) => (),
        };
    }

    for _in in &input {
        println!("{_in}");
    }

    let mut winning_numbers: Vec<HashSet<u64>> = Vec::new();
    let mut original_numbers: Vec<HashSet<u64>> = Vec::new();
    for _in in &input {
        for (idx, splitted) in _in.split(" | ").enumerate() {
            let mut numbers: HashSet<u64> = HashSet::new();
            for str in splitted.trim().split(" ") {
                if str.trim().len() > 0 {
                    numbers.insert(str.trim().parse().unwrap());
                }
            }
            if idx == 0 {
                winning_numbers.push(numbers);
            } else {
                original_numbers.push(numbers)
            }
        }
    }

    let mut answer: u64 = 0;
    let mut map: HashMap<u64, u64> = HashMap::new();
    for i in 0..original_numbers.len() as u64 {
        map.insert(i, 1);
    }
    for (idx, set) in original_numbers.iter().enumerate() {
        let idx = idx as u64;
        let intersection: HashSet<&u64> = set
            .intersection(winning_numbers.get(idx as usize).unwrap())
            .collect();
        let common_numbers = cmp::min(intersection.len(), original_numbers.len()) as u64;
        for i in idx + 1..=idx + common_numbers {
            let value = map.get(&i).unwrap();
            map.insert(i, map.get(&idx).unwrap() + value);
        }
    }
    for (key, value) in map {
        println!("Card {} => {value}", key + 1);
        answer += value;
    }
    println!("{}", answer);
}
