const body_regionsRoutes = require("./body_regions");
const specific_regionsRoutes = require("./specific_regions");
// const causesRoutes = require("./causes");

const constructor = (app) => {
  app.get("/PocketDoc", (req, res) => {
    res.status(200).json();
  });

  app.use("/PocketDoc/body_regions", body_regionsRoutes);
  app.use("/PocketDoc/specific_regions", specific_regionsRoutes);
// app.use("/PocketDoc/causes", causesRoutes);

  app.use("*", (req, res) => {
    res.sendStatus(404);
  });
};

module.exports = constructor;
