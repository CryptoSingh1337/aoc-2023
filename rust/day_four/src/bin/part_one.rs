use std::{collections::HashSet, fs::File, io::BufRead, io::BufReader};

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

    let mut winning_numbers: Vec<HashSet<u32>> = Vec::new();
    let mut original_numbers: Vec<HashSet<u32>> = Vec::new();
    for _in in &input {
        for (idx, splitted) in _in.split(" | ").enumerate() {
            // println!("Splitted: {}", splitted.trim());
            let mut numbers: HashSet<u32> = HashSet::new();
            for str in splitted.trim().split(" ") {
                // println!("Number: {}, length: {}", str, str.len());
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
    for (idx, set) in original_numbers.iter().enumerate() {
        let intersection: HashSet<&u32> = set
            .intersection(winning_numbers.get(idx).unwrap())
            .collect();
        let base: u32 = 2;
        if intersection.len() > 0 {
            let points = base.pow((intersection.len() - 1) as u32) as u64;
            answer += points;
        }
    }
    println!("{}", answer);
}
