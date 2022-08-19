import React from "react";
import PropTypes from "prop-types";
import {
  Box,
  Grid,
  Button,
  Typography,
  Tab,
  Tabs,
  Card,
  CardMedia,
  CardActions,
} from "@mui/material";

function TabPanel(props) {
  const { children, value, index, ...other } = props;

  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`simple-tabpanel-${index}`}
      aria-labelledby={`simple-tab-${index}`}
      {...other}
    >
      {value === index && (
        <Box sx={{ p: 3 }}>
          <Typography>{children}</Typography>
        </Box>
      )}
    </div>
  );
}

TabPanel.propTypes = {
  children: PropTypes.node,
  index: PropTypes.number.isRequired,
  value: PropTypes.number.isRequired,
};

function GetWorksList({ worksStatusList, dashNav, pageNav }) {
  return (
    <Grid container spacing={3}>
      {worksStatusList.map((list) => (
        <Grid item>
          <Box
            sx={{
              maxWidth: 128,
              display: "flex",
              flex: 1,
              flexDirection: "column",
            }}
          >
            <Card>
              <CardMedia
                component="img"
                height="140"
                image={list.cover}
                alt={list.label}
              />
              <CardActions
                disableSpacing={true}
                sx={{ flexDirection: "column" }}
              >
                <Button variant="contained" onClick={dashNav}>
                  Dashboard
                </Button>
                <Button variant="contained" onClick={pageNav}>
                  Page
                </Button>
              </CardActions>
            </Card>
          </Box>
        </Grid>
      ))}
    </Grid>
  );
}

export default function WorksList({
  viewTab,
  onTabSelect,
  workTitle,
  onDashNav,
  onPageNav,
  ongoing,
  completed,
  hiatus,
}) {
  const [value, setValue] = React.useState(viewTab);

  return (
    <Box sx={{ width: "100%", boxShadow: 2, borderRadius: 3 }}>
      <Box sx={{ borderBottom: 1, borderColor: "divider" }}>
        <Tabs value={value} onChange={onTabSelect} variant="fullWidth">
          <Tab label="Ongoing" />
          <Tab label="Completed" />
          <Tab label="Hiatus" />
        </Tabs>
      </Box>
      <TabPanel value={value} index={0}>
        <GetWorksList
          worksStatusList={ongoing}
          dashNav={onDashNav}
          pageNav={onPageNav}
        />
      </TabPanel>
      <TabPanel value={value} index={1}>
        <GetWorksList
          worksStatusList={completed}
          dashNav={onDashNav}
          pageNav={onPageNav}
        />
      </TabPanel>
      <TabPanel value={value} index={2}>
        <GetWorksList
          worksStatusList={hiatus}
          dashNav={onDashNav}
          pageNav={onPageNav}
        />
      </TabPanel>
    </Box>
  );
}
