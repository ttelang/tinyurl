const { Router } = require("express");

const { encode } = require("../service/encoder");
const { 
  findOriginalURL
} = require("../service/tinyurl");
const route = Router();

route.get("/:code", async (req, res) => {
  const code = req.params.code;
  // TODO: validate code is available
  console.log(code);
  const url = await findOriginalURL(code);
  console.log("redirecting to " + url + "...");
  return res.redirect(302, "https://" + url.replace('"', '').replace('"', ''));
});

module.exports = route;