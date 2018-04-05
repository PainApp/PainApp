const express = require("express");
const router = express.Router();
const db = require("../connection");

router.post("/", async(req,res) => {
  try { 
    if (req.is("json") != "json") {
      res.sendStatus(400);
    }
    else {
      var keys = Object.keys(req.body);
      if (keys.length != 1 || keys[0] != "id") {
        res.sendStatus(400);
      } else {
        var query = "SELECT id, name, classification FROM causes WHERE id = ?";
        const conn = await db.getConnection();
        const [results, fields] = await conn.query(query, req.body.id);
        conn.release();
        if (results.length != 1) {
          res.sendStatus(404);
        } else {
          var result = results[0];
          res.json(result);
        }
      }
    }
  } catch (err) {
    console.log(err);
    res.sendStatus(404);
  }
});

module.exports = router;
