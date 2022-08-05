import React from "react";
import { Stack, Typography, Box } from "@mui/material";

const ChapterNote = ({ text, isVisible }) => {
  // const [visible, setVisible] = React.useState(isVisible);
  return (
    <Box
      sx={{
        width: "100%",
        height: "100%",
        backgroundColor: "background.paper",
        boxShadow: 2,
        borderRadius: 3,
        p: 1,
        visibility: { isVisible },
      }}
    >
      {/* look into gutterBottom */}
      <Typography variant="body1" gutterBottom>
        {text}
      </Typography>
    </Box>
  );
};

export default ChapterNote;
