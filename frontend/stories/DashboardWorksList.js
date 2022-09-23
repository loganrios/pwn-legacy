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

function GetWorksList({ worksStatusList }) {
  return (
    <Grid container spacing={3}>
      {worksStatusList?.map((item) => (
        <Grid item xs={12}>
          <Box
            sx={{
              display: "flex",
              flex: 1,
              flexDirection: "row",
              gap: 1,
            }}
          >
            <Card>
              <CardMedia
                component="img"
                height="140"
                image={item.cover}
                alt={item.label}
              />
            </Card>
            <Box
              sx={{
                display: "flex",
                flexDirection: "column",
                gap: 1,
                alignSelf: "center",
              }}
            >
              <Button variant="contained" onClick={item.onDashNav}>
                Dashboard
              </Button>
              <Button variant="contained" onClick={item.onPageNav}>
                Page
              </Button>
              <Button variant="contained" onClick={item.onAddChapter}>
                Add Chapter
              </Button>
            </Box>
          </Box>
        </Grid>
      ))}
    </Grid>
  );
}

export default function DashboardWorksList({
  viewTab,
  ongoing,
  completed,
  hiatus,
}) {
  const [value, setValue] = React.useState(viewTab);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  return (
    <Box
      sx={{
        display: "flex",
        flexDirection: "row",
        width: "100%",
        boxShadow: 2,
        borderRadius: 3,
      }}
    >
      <Box sx={{ borderBottom: 1, borderColor: "divider" }}>
        <Tabs
          orientation="vertical"
          value={value}
          onChange={handleChange}
          variant="fullWidth"
        >
          <Tab label="Ongoing" />
          <Tab label="Completed" />
          <Tab label="Hiatus" />
        </Tabs>
      </Box>
      <TabPanel value={value} index={0}>
        <GetWorksList worksStatusList={ongoing} />
      </TabPanel>
      <TabPanel value={value} index={1}>
        <GetWorksList worksStatusList={completed} />
      </TabPanel>
      <TabPanel value={value} index={2}>
        <GetWorksList worksStatusList={hiatus} />
      </TabPanel>
    </Box>
  );
}
