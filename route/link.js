const { Router } = require("express");

const {
  createShortURL, 
  findOriginalURL
} = require("../service/tinyurl");

const route = Router();

/**
 * POST /api/link
 * BODY
 *      link: http://xxxx.xxxx/xxxx/xxxx
 *      --- optional ---
 *      code: xxxxx
 */
route.post("/", async (req, res) => {
  const link = req.body.link;
  console.log("link -> " + link);
  
  let shortUrl;
  try {
    shortUrl = await createShortURL(link);
  } catch (e) {
    return res.status(400).json({error: e.message});
  }
  
  return res.json(shortUrl);
});

/**
 * GET /api/link/xxxxx
 * RESPONSE
 *      link:
 */
route.get("/:code", async (req, res) => {
  const code = req.params.code;
  // TODO: validate code is available

  const url = await findOriginalURL(code);

  if (url) {
    return res.json(url);
  } else {
    return res.status(404).json({ error: "No such shortUrl" });
  }
});

module.exports = route;