import React from "react";
import { Typography, Box } from "@mui/material";
import AutoStoriesIcon from "@mui/icons-material/AutoStories";

const ChapterBody = ({ text, title }) => {
  return (
    <Box
      sx={{
        display: "flex",
        justifyContent: "center",
        flexDirection: "column",
        alignItems: "center",
        backgroundColor: "background.paper",
        maxWidth: 900,
        boxShadow: 2,
        borderRadius: 3,
        p: 2,
        mx: "auto",
        gap: 2,
      }}
    >
      <Box>
        <Typography variant="h5">{title}</Typography>
      </Box>
      {text}
    </Box>
  );
};

export default ChapterBody;
