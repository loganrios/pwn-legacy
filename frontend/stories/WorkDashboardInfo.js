import React from "react";
import { Box, Typography, Button, ButtonBase } from "@mui/material";
import {
  styled,
  createTheme,
  responsiveFontSizes,
  ThemeProvider,
} from "@mui/material/styles";
import EditIcon from "@mui/icons-material/Edit";
import LaunchIcon from "@mui/icons-material/Launch";

let theme = createTheme();
theme = responsiveFontSizes(theme);

const Img = styled("img")({
  margin: "auto",
  display: "block",
  maxWidth: "100%",
  maxHeight: "100%",
});

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  bgcolor: "background.paper",
  border: "2px solid #000",
  boxShadow: 24,
  pt: 2,
  px: 4,
  pb: 3,
};

const WorkDashboardInfo = ({
  workTitle,
  blurb,
  image,
  onEdit,
  onGoToPage,
  onEditCover,
}) => {
  return (
    <div>
      <ThemeProvider theme={theme}>
        <Box
          sx={{
            display: "grid",
            borderRadius: 2,
            boxShadow: 3,
            gridTemplateColumns: "auto",
            gap: 1,
            p: { xs: 0, sm: 2 },
            alignItems: "center",
            justifyContent: "center",
            gridTemplateRows: "auto",
            gridTemplateAreas: {
              xs: `"title title title title"
                   "cover cover cover cover"
                   "edit  edit  edit  edit"
                   "gtp   gtp   gtp   gtp"
                   "blurb blurb blurb blurb"
                   "blurb blurb blurb blurb"`,
              sm: `"cover cover title title title edit  gtp"
                   "cover cover blurb blurb blurb blurb blurb"
                   "cover cover blurb blurb blurb blurb blurb"`,
            },
          }}
        >
          <Box
            sx={{
              gridArea: "title",
              display: "inline-flex",
              alignItems: "center",
              justifyContent: "center",
            }}
          >
            <Typography variant="h4" align="center">
              {workTitle}
            </Typography>
          </Box>
          <Box
            sx={{
              gridArea: "edit",
              display: "flex",
              justifyContent: "center",
            }}
          >
            <Button
              variant="contained"
              aria-label="follow"
              startIcon={<EditIcon />}
              onClick={onEdit}
            >
              Edit
            </Button>
          </Box>
          <Box
            sx={{
              gridArea: "gtp",
              display: "flex",
              justifyContent: "center",
            }}
          >
            <Button
              variant="contained"
              aria-label="go to page"
              startIcon={<LaunchIcon />}
              onClick={onGoToPage}
            >
              Go to page
            </Button>
          </Box>
          <Box
            sx={{
              gridArea: "cover",
              display: "flex",
              alignItems: "center",
              justifyContent: "center",
            }}
          >
            <ButtonBase onClick={onEditCover} sx={{ width: 90, height: 144 }}>
              <Img alt="cover" src={image} />
            </ButtonBase>
          </Box>
          <Box
            sx={{
              gridArea: "blurb",
              display: "flex",
              alignItems: "flex-start",
              ml: 2,
              mr: 1,
            }}
          >
            <Typography variant="body1">{blurb}</Typography>
          </Box>
        </Box>
      </ThemeProvider>
    </div>
  );
};

export default WorkDashboardInfo;
