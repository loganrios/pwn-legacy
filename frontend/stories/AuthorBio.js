import React from "react";
import {
  Box,
  Avatar,
  Paper,
  Grid,
  Typography,
  Button,
  ButtonBase,
} from "@mui/material";
import { styled } from "@mui/material/styles";

const Img = styled("img")({
  margin: "auto",
  display: "block",
  maxWidth: "100%",
  maxHeight: "100%",
});

const AuthorBio = ({
  username,
  bioText,
  statsText,
  image,
  isOwner,
  onFollow,
  onSponsor,
}) => {
  return (
    <Box
      sx={{
        flexGrow: 1,
        overflow: "hidden",
        px: 0.1,
      }}
    >
      <Paper
        sx={{
          my: 0.1,
          mx: "auto",
          p: 0.1,
          maxWidth: 800,
        }}
      >
        <Grid container justifyContent="center" alignItems="center" spacing={1}>
          <Grid item>
            <Box>
              <ButtonBase sx={{ width: 128, height: 128 }}>
                <Img alt="default" src={image} />
              </ButtonBase>
              <Box
                sx={{
                  flexGrow: 1,
                  overflow: "hidden",
                  px: 0.1,
                  boxShadow: 2,
                  borderRadius: 3,
                }}
              >
                <b1>{statsText}</b1>
              </Box>
            </Box>
          </Grid>
          <Grid item xs={12} sm container flexGrow={1}>
            <h1>{username}</h1>
            <Box
              sx={{
                display: "flex",
                flexDirection: "row",
                justifyContent: "left",
                typography: "p",
                mx: "auto",
                backgroundColor: "background.paper",
                boxShadow: 2,
                borderRadius: 3,
              }}
            >
              <p>{bioText}</p>
            </Box>
          </Grid>
          <Grid item>
            <Button variant="contained" aria-label="follow" onClick={onFollow}>
              Follow
            </Button>
            {isOwner ? (
              <Button variant="contained" disabled>
                Sponsor
              </Button>
            ) : (
              <Button
                variant="contained"
                aria-label="sponsor"
                onClick={onSponsor}
              >
                Sponsor
              </Button>
            )}
          </Grid>
        </Grid>
      </Paper>
    </Box>
  );
};

export default AuthorBio;
