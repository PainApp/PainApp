const express = require("express");
const router = express.Router();
const db = require("../connection");

router.get("/", async(req, res) => {
  try {
    const conn = await db.getConnection();
    const [results, fields] = await conn.query("SELECT id, name FROM body_regions")
    conn.release();
    res.json({ "body_regions" : results });
  } catch (err) {
    console.log(err);
    res.sendStatus(404);
  }
});

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
        var query = "SELECT * FROM body_regions WHERE id = ?;";
        query += "SELECT id, name FROM specific_regions WHERE body_region_id = ?"
        const conn = await db.getConnection();
        const [results, fields] = await conn.query(query, [req.body.id, req.body.id]);
        conn.release();
        if (results[0].length != 1) {
          res.sendStatus(404);
        } else {
          var response = results[0][0];
          response["specific_regions"] = results[1];
          res.json(response);
        }
      }
    }
  } catch (err) {
    console.log(err);
    res.sendStatus(404);
  }
});

module.exports = router;
