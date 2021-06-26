const ALPHABET =
  "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_";
const CHAR_MAP = {};

ALPHABET.split("").forEach((v, i) => {
  CHAR_MAP[v] = i;
});

/**
 * converts a Base64 to String 
 */
function decode(num) {
  let chars = [];
  let q = num;
  while (q > 0) {
    let r = q % 64;
    chars.push(ALPHABET.charAt(r));
    q = parseInt(q / 64);
  }
  return chars.reverse().join("");
}

/**
 * converts a String to Base64 
 */
function encode(str) {
  str = str + "";
  let chars = str.split("").reverse().join("");
  let num = 0;
  for (let i = 0; i < chars.length; i++) {
    num += CHAR_MAP[chars[i]] * Math.pow(64, i);
  }
  return num;
}

module.exports = {
  decode,
  encode,
};