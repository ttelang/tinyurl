const Database = require("@replit/database")
const db = new Database()

const { encode, decode } = require("../service/encoder");

const RANGE = 999999999999; 


async function findOriginalURL(shortURL) {
  const id = encode(shortURL);
  
  console.log("finding " + id + " for shortURL " + shortURL);

  url = await db.get(id, {raw:true});
  console.log(url);
  return url; 
}

async function createShortURL(link) {
  const code = parseInt(Math.random() * RANGE);
  
  url = await db.get(code);
  if (url) {
      createShortURL(link);
  }

  console.log("code " + code + " link " + link);
  
  await db.set(code, link);
  
  const shortURL = "https://urlshrtnr.taruntelang.repl.co/" + decode(code);
  return shortURL;
}

module.exports = {
  createShortURL, 
  findOriginalURL
};